import { logClass } from './class-decorator';
import { logMethod } from './method-decorator';

// decorator factory - which calls the corresponding decorators based on arguments passed
export function log(...args: any[]) {
    switch (args.length) {
        case 3: // can be method or parameter decorator
            if (typeof args[2] === 'number') { // if 3rd argument is number then its index so its parameter decorator
                return logParameter.apply(this, args);
            }
            return logMethod.apply(this, args);
        case 2: // property decorator
            return logProperty.apply(this, args);
        case 1: // class decorator
            return logClass.apply(this, args);
        default: // invalid size of arguments
            throw new Error('Not a valid decorator');
    }
}
