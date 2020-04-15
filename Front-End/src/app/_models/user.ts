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

  constructor(
    id: number,
    firstName: string,
    lastName: string,
    email: string,
    userName: string,
    password: string,
    positionId: number,
    roleId: number,
    position: Position,
    role: Role
  ) { 
    // this.id=id;
    // this.firstName=firstName,
    // this.lastName=lastName,
    // this.email=email,
    // this.userName=userName;
    // this.password=password;
    // this.positionId=positionId,
    // this.roleId=roleId;
    this.position=position,
    this.role=role;
  }

}

export enum RoleEnum {
  User = 2,
  Admin = 1
}


export class Position {
  id: number;
  key: string;
  descption: string;
  constructor(
    id: number,
    key: string,
    descption: string
  ) { }
}

export class Role {
  id: number;
  name: string;
  constructor(
    id: number,
    name: string,
  ) { }
}