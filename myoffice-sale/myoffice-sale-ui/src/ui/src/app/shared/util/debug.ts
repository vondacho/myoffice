import { Optional } from './functional';

export function toJson(object: Optional<object>): string {
    return object ? JSON.stringify(object) : 'undefined';
}
