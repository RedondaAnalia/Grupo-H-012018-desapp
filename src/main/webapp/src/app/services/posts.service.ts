import { Injectable } from '@angular/core';

import { Post } from '../interfaces/post.interface';
import { Coord } from '../interfaces/coord.interface';

@Injectable()
export class PostsService {
    private coord1:Coord={lat:0, lng:0};

    private posts:Post[] = [
        {
            id:0,
            vehicleType:"AUTO",
            capacity:"2",
            location:"Bernal",
            description:"Es un auto antiguo, traccion a sangre y mucha voluntad",
            phone: 1142836765 ,
            rentalDate: "05-09-2018",
            costPerDay:"80",
            photos:"assets/img/pedropicapiedrasauto.jpg",
            coordPickUp: {lat:-34.705857, lng:-58.278498}    
        },
        {
            id: 1,
            vehicleType:"CAMIONETA",
            capacity:"3",
            location:"Quilmes",
            description:"Furgoneta al más puro estilo 'Van Power Flower', tan propia de la época hippie",
            phone: 1158967245 ,
            rentalDate: "04-03-2018",
            costPerDay:"190",
            photos:"assets/img/ScoobyDoo-camioneta.jpg",
            coordPickUp: this.coord1    
        },
        {
            id:2,
            vehicleType:"AUTO",
            capacity:"4",
            location:"Springfield",
            description:"Plymouth Junkerolla de 1986. Se trata de un vehiculo muy resistente, porque a pesar de lo que le hizo Homero en 28 años, sigue funcionando.",
            phone: 1128405384 ,
            rentalDate: "08-09-2018",
            costPerDay:"185",
            photos:"assets/img/los-simpsons-auto.jpg",
            coordPickUp: this.coord1    
        },
        
      ];

    constructor() {
        console.log("Servicio listo para usar!!");
     }

     getPosts():Post[]{
         return this.posts;
     }

     getPost(idx:string):Post{
         return this.posts[idx];
     }

}

