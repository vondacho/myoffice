export interface Attribute {
  name: string;
  type: AttributeType;
  editable: boolean;
  value?: string;
  options?: AttributeOption[];
  icon?: string;
  label?: string;
  url?: string | string[];
  tooltip?: string;
}

export type AttributeType = String | Date | Boolean | Selection;

export interface AttributeOption {
  label: string;
  value: any;
}
