import {Type} from '@angular/core';

export class OperationPerformed {
  static readonly type = 'Event.OperationPerformed';

  constructor(public readonly command: string, public readonly event: string) {
  }

  static of<C, E>(command: Type<C>, event: E): OperationPerformed {
    return new OperationPerformed(command['type'], 'Event.' + event.constructor.name);
  }
}
