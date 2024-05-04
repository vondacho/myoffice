export interface SingleSort {
  attribute: string;
  direction: SortDirection;
}

export type SortCombination = SingleSort[];
export type SortDirection = 'asc' | 'desc' | '';

export const sortOf = (att: string, dir: SortDirection = 'asc') => <SingleSort>{attribute: att, direction: dir};
export const ascOf = (att: string) => sortOf(att, 'asc');
export const descOf = (att: string) => sortOf(att, 'desc');
