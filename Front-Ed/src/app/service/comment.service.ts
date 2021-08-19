import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { CommentsDTO } from '../model/CommentsDTO';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(
    private http: HttpClient
  ) { }

  token = {
    headers: new HttpHeaders().set("Authorization", environment.token)
  }

  postComment(commentsDto: CommentsDTO): Observable<Comment> {
    return this.http.post<Comment>('http://localhost:8080/comment/post', commentsDto, this.token);
  }
  
}
