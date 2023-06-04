import { AppointmentStatus } from "./appointment-status.enum"

export interface AppointmentsCountByStatus{
    status : AppointmentStatus
    counter : number
}