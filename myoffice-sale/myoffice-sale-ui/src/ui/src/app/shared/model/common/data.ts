import {Links, Resource, RESOURCE_LINKS_KEY, EmbeddedResource} from './resource';
import {Page} from './pageable';

export type SData<T> = T | Resource<T>;
export type CData<T> = EmbeddedResource<{ content: Array<Resource<T>> }>;
export type PData<T> = Page<T> | Resource<Page<Resource<T>>>;

export function isResource<T>(data: SData<T>): boolean {
  return Object.keys(data).includes(RESOURCE_LINKS_KEY);
}

export function resourceLinks<T>(data: SData<T> | CData<T> | PData<T>): Links {
  return isResource(data) ? data[RESOURCE_LINKS_KEY] : [];
}

export function embedded<T>(data: CData<T>): T[] {
  return data._embedded ? data._embedded.content : [];
}

export function hasLink<T>(resource: Resource<T>, name: string): boolean {
  return !!resourceLinks(resource)[name];
}

