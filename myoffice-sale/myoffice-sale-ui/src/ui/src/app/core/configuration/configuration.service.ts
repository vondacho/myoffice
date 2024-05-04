import {Injectable} from '@angular/core';
import {of} from 'rxjs';
import {tap} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';

@Injectable({providedIn: 'root'})
export class ConfigurationService {
  configurations: object;

  constructor(private http: HttpClient) {
  }

  getConfigs(): Promise<Object> {
    console.log('loading configurations');
    return of(dummyConfigs) // this could be a http request
      .pipe(
        tap(config => {
          this.configurations = config;
        })
      )
      .toPromise();
  }
}

const dummyConfigs: Object = {
  APIEndpoint: 'url_here',
  apiKey: 'abcdee'
};
