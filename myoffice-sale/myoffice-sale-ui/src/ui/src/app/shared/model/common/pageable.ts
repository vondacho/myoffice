export interface PageSpec {
  number: number;
  size: number;
}

export interface PageInfo extends PageSpec {
  totalElements: number;
  totalPages: number;
}

export interface Page<T> extends PageInfo {
  content: Array<T>;
}

export const EMPTY_PAGE: PageInfo = {
  number: 0, size: 0, totalElements: 0, totalPages: 0
};

export const DEFAULT_PAGE: PageInfo = {
  number: 0, size: 10, totalElements: 0, totalPages: 0
};

export const pageOf = (id: number, size: number) => <PageInfo>{number: id, size: size};
