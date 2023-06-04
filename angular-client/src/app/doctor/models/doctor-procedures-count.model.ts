export interface InterventionsByProcedure{
    month : string,
    procedures : {id : number, name : string, total: number}[]
}