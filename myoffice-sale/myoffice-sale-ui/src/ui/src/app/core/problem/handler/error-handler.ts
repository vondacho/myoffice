import {ErrorHandler} from '@angular/core';
import { log } from 'shared/util/decorator/logging/log-decorator';

export class DefaultErrorHandler implements ErrorHandler {
    @log
    handleError(error: any): void {
        // do something with the exception
    }
}
