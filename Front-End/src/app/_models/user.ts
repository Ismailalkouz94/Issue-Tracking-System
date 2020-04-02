export class User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  userName: string;
  password: string;
  positionId: number;
  roleId: number
  position: Position;
  role: Role
}

export enum RoleEnum {
  User = 2,
  Admin = 1
}


export class Position {
  id: number;
  key: string;
  descption: string;
}

export class Role {
  id: number;
  name: string;
}