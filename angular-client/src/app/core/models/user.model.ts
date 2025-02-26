import { UserType } from "./user-type.enum";

export interface User {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    userType: UserType;
    jwtToken: string;
}