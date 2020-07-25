import {InvoiceItem} from "./invoice-item";
import {Client} from "../../client/client";

export class Invoice {
  id: number;
  description: string;
  observation: string;
  items: Array<InvoiceItem> = [];
  client: Client;
  total: number;
  createAt: string;
}
