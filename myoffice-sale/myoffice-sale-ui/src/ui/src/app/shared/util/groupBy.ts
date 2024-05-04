export function groupBy<V, K>(list: V[], keyGetter: (it: V) => K): Map<K, V[]> {
  const result = new Map();
  list.forEach(it => {
    const key = keyGetter(it);
    const collection = result.get(key);
    if (!collection) {
      result.set(key, [it]);
    } else {
      collection.push(it);
    }
  });
  return result;
}
