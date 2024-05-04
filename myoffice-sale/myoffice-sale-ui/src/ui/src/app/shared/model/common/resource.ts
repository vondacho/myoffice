export type Operation = string;
export interface Links {
  [key: string]: { href: string };
}

export interface Hateoas {
  _links?: Links;
}

export type Resource<T> = T & Hateoas;
export type EmbeddedResource<T> = { _embedded: T } & Hateoas;

export const RESOURCE_LINKS_KEY = '_links';
export const EMBEDDED_RESOURCE_KEY = '_embedded';

export const EMPTY_EMBEDDED_RESOURCE = { _embedded: { content: [] }};
