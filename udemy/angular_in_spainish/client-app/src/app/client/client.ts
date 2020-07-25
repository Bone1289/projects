import {Region} from "./region";
import {Invoice} from "../invoices/models/invoice";

export class Client {
  id: number;
  firstName: string;
  lastName: string;
  createAt: string;
  email: string;
  photo: string;
  region: Region;
  invoice: Array<Invoice> = [];
}
