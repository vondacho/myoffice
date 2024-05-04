import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {Observable, timer} from 'rxjs';
import {debounce, filter, map, startWith, switchMap} from 'rxjs/operators';
import {Folder, FolderId, FolderState} from 'shared/model/folder/folder';
import {SData} from 'shared/model/common/data';
import {ifDefinedDo, Optional, ifDefinedMapElseDefault} from 'shared/util/functional';
import {isNotEmptyPredicate} from 'shared/util/empty';
import { FolderRepository } from 'core/api/folder/folder-repository.service';

@Component({
  selector: 'app-folder-selector',
  templateUrl: './folder-selector.component.html',
  styleUrls: ['./folder-selector.component.css']
})
export class FolderSelectorComponent implements OnInit {

  @Output() onSelect = new EventEmitter<FolderId>();
  folders$: Observable<SData<FolderState>[]>;

  private fcFolder: FormControl;

  constructor(private folderRepository: FolderRepository) {
  }

  ngOnInit(): void {
    this.buildSelectorField();
    this.initializeFolderStream();
  }

  displayFn(folder?: Folder): Optional<string> {
    return ifDefinedMapElseDefault(folder, f => `${f.name}`, () => '');
  }

  reset(): void {
    this.fcFolder.reset();
  }

  canBeApplied(): boolean {
    return !!this.fcFolder.value;
  }

  select(): void {
    ifDefinedDo(this.fcFolder.value, value => {
      this.onSelect.emit(value.id);
      this.reset();
    });
  }

  private buildSelectorField(): void {
    this.fcFolder = new FormControl('');
  }

  private initializeFolderStream(): void {
    this.folders$ = this.fcFolder.valueChanges
      .pipe(
        startWith<string | FolderState>(''),
        filter(isNotEmptyPredicate),
        map(value => typeof value === 'string' ? value : value.name),
        debounce(() => timer(1000)),
        switchMap(term => this.folderRepository.search$(term)),
        map(page => page.content));
  }
}

