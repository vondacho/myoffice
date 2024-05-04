import * as _ from 'lodash';

export type Optional<T> = T | undefined;
export type Consumer<T> = (t: T) => void;
export type Supplier<T> = () => T;
export type Predicate<T> = (t: T) => boolean;

export function ifDefinedDo<T>(value: Optional<T>, consumer: Consumer<T>, _else?: Consumer<void>): void {
  if (value) {
    consumer(value);
  } else if (_else) {
    _else(undefined);
  }
}

export function ifDefinedMap<T, U>(value: Optional<T>, fn: (t: T) => U, defaultBehavior?: Supplier<U>): Optional<U> {
  return value ? fn(value) : defaultBehavior ? defaultBehavior() : undefined;
}

export function ifDefinedMapElseDefault<T, U>(value: Optional<T>, fn: (t: T) => U, defaultBehavior: Supplier<U>): U {
  return ifDefinedMap(value, fn, defaultBehavior) as U;
}

export function ifNotEmptyDo<T>(value: Optional<T>, consumer: Consumer<T>, _else?: Consumer<void>): void {
  if (!_.isEmpty(value)) {
    consumer(value as T);
  } else if (_else) {
    _else(undefined);
  }
}

export function ifNotEmptyMap<T, U>(value: Optional<T>, fn: (t: T) => U, defaultBehavior?: Supplier<U>): Optional<U> {
  return _.isEmpty(value) ? defaultBehavior ? defaultBehavior() : undefined : fn(value as T);
}

export function ifNotEmptyMapElseDefault<T, U>(value: Optional<T>, fn: (t: T) => U, defaultBehavior: Supplier<U>): U {
  return ifNotEmptyMap(value, fn, defaultBehavior) as U;
}
