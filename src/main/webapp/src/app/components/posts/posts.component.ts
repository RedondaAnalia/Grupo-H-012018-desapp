import { Component, OnInit } from "@angular/core";
import { PostsService } from "../../services/posts.service";
import { Post } from "../../interfaces/post.interface";
import { Router } from "@angular/router";

@Component({
  selector: "app-find-car",
  templateUrl: "./posts.component.html"
})
export class PostsComponent implements OnInit {

  posts: Post[] = [];


  constructor( private _postsService:PostsService,
               private _router: Router
              ) {


  }

  // Esto se ejecuta una vez que la pagina ya esta renderizada.
  ngOnInit() {
    this.posts = this._postsService.getPosts();
    // console.log(this.heroes);
  }

  verPost( idx: number ) {
    this._router.navigate(["/post", idx]);
  }

}
