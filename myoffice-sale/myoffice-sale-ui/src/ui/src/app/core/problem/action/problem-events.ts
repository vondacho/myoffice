import {Problem} from 'shared/model/problem/problem';

export class DomainProblemOccured {
  static readonly type = 'Event.DomainProblemOccured';
  constructor(public problem: Problem) {
  }
}

export class TechnicalProblemOccured {
  static readonly type = 'Event.TechnicalProblemOccured';
  constructor(public problem: Problem) {
  }
}
