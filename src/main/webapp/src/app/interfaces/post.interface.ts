
import { Coord } from '../interfaces/coord.interface';


export interface Post{
    id: number;
    vehicleType:string;
    capacity:string;
    location:string;
    description:string;
    phone:number;
    rentalDate:string;
    costPerDay:string;
    photos:string;
    coordPickUp:Coord;    
};
