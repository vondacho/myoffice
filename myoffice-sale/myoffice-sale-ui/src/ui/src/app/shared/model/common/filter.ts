export interface SingleFilter {
  attribute: string;
  operation: FilterOperator;
  value: string;
}

export type FilterCombination = SingleFilter[];
export type FilterOperator = ':' | '.' | '=' | '>' | '<';

export const startsOf = (att: string, value: string) => <SingleFilter>{attribute: att, operation: ':', value: value};
export const containsOf = (att: string, value: string) => <SingleFilter>{attribute: att, operation: '.', value: value};
export const eqOf = (att: string, value: string) => <SingleFilter>{attribute: att, operation: '=', value: value};
export const gtOf = (att: string, value: string) => <SingleFilter>{attribute: att, operation: '>', value: value};
export const stOf = (att: string, value: string) => <SingleFilter>{attribute: att, operation: '<', value: value};
