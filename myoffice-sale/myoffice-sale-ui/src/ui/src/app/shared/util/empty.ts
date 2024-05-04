export function isNotEmptyPredicate(object: any): boolean {
    return object && object !== undefined && object !== {};
}

export function isDefinedPredicate(object: any): boolean {
  return !!object;
}
