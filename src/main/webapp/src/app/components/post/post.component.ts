
import { Component, OnInit } from "@angular/core";
import { Post } from "../../interfaces/post.interface";
import { ActivatedRoute, Router } from "@angular/router";
import { PostsService } from "../../services/posts.service";


@Component({
  selector: 'app-post',
  templateUrl: "./post.component.html",
})


export class PostComponent implements OnInit {

  private post: Post;
  zoom: number= 15;
  // Start position
  lat: number = -34.603418;
  lng: number = -58.381592;
  // Markers
  markers: marker[] = [

  ];

  constructor(private activatedRoute: ActivatedRoute,
              private _postsService: PostsService,
              private _router:Router) {

      this.activatedRoute.params.subscribe( params => {
        // Se pone id porque es el nombre del parametro que esta en el routing ((/post/:id))!!!!!
          this.post = this._postsService.getPost(params["id"]);

      })
  }

  ngOnInit() {
    this.lat= this.post.coordPickUp.lat;
    this.lng= this.post.coordPickUp.lng;
    let marker: marker={name:"Lugar de Retiro",lat: this.lat, lng: this.lng,draggable:false};
    this.markers.push(marker);
  }

  reservar(id: number){
    this._router.navigate(["/home"]);
  }


}

interface marker{
  name?:string;
  lat: number;
  lng: number;
  draggable: boolean;
}
