import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Message } from '../model/Message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient) { }

  token = {
    headers: new HttpHeaders().set("Authorization", environment.token)
  }

  getAllMessage(): Observable<Message[]>
  {
    return this.http.get<Message[]>('http://localhost:8080/message/all', this.token)
  }

  postMessage(message: Message): Observable<Message>
  {
    return this.http.post<Message>('http://localhost:8080/message/post', message, this.token)
  }


}
