import {Moment} from 'moment';
import { Optional } from 'shared/util/functional';

export class IsoDate {

  static toString(date: Optional<string | Moment>): Optional<string> {
    if (date == null || date === undefined) {
      return undefined;
    }
    if (typeof date === 'string') {
      return date;
    } else {
      return date.format('YYYY-MM-DD');
    }
  }
}
