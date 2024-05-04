import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { Actions, ofActionDispatched, Store } from '@ngxs/store';
import { LastProblemState } from 'core/problem/state/last-problem-state';
import { Subscription } from 'rxjs';
import { filter } from 'rxjs/operators';
import { OperationPerformed } from 'shared/event/events';
import { Problem } from 'shared/model/problem/problem';
import { isDefined } from '@angular/compiler/src/util';

@Injectable()
export class Notifier {

  private subscriptions: Subscription[] = [];

  constructor(
    private snackBar: MatSnackBar,
    private store: Store,
    private actions$: Actions) {
  }

  initialize(): void {
    this.subscriptions = [
      this.store.select(LastProblemState).pipe(filter(isDefined))
        .subscribe(problem => this.onProblem(problem)),
      this.actions$.pipe(ofActionDispatched(OperationPerformed))
        .subscribe(op => this.onSuccess(op))
    ];
  }

  release(): void {
    this.subscriptions.forEach(s => s.unsubscribe());
    this.subscriptions = [];
  }

  info(message: string): void {
    this.snackBar.open(message, undefined, {duration: 3000});
  }

  failure(message: string): void {
    this.snackBar.open(message, undefined, {duration: 3000});
  }

  private onProblem(problem: Problem): void {
    this.failure(problem.message);
  }

  private onSuccess({command, event}: OperationPerformed): void {
    this.info(`L'opération ${command} s'est terminée avec ${event}.`);
  }
}
