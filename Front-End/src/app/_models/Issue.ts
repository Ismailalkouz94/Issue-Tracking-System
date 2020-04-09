export class Issue {
  id: number;
  title: string;
  description: string;
  owner: number;
  assignTo: number;
  type: number;
  status: number;

  constructor(
    id: number,
    title: string,
    description: string,
    owner: number,
    assignTo: number,
    type: number,
    status: number,) {

  }

}