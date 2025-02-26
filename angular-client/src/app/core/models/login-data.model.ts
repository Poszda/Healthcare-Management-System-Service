import { Admin } from "./admin.model";
import { Doctor } from "./doctor.model";
import { Patient } from "./patient.model";
import { UserType } from "./user-type.enum";

export interface LoginData{
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    userType: UserType;
    jwtToken: string;
    patient?:Patient;
    doctor?: Doctor;
    admin?: Admin;
}