import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Business } from '../model/Business';
import { Message } from '../model/Message';
import { MessageBusinessDTO } from '../model/MessageBusinessDTO';
import { MessageCommunityDTO } from '../model/MessageCommunityDTO';
import { MessageProfileDTO } from '../model/MessageProfileDTO';
import { ProfileBusinessDTO } from '../model/ProfileBusinessDTO';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(
    private http: HttpClient
  ) { }

  token = {
    headers: new HttpHeaders().set("Authorization", environment.token)
  }

  getAllMessage(): Observable<Message[]> {
    return this.http.get<Message[]>('http://localhost:8080/message/all', this.token)
  }

  postMessage(messageProfileDto: MessageProfileDTO): Observable<Message> {
    return this.http.post<Message>('http://localhost:8080/message/post', messageProfileDto, this.token)
  }

  postMessageOnCommunity(messageCommunityDto: MessageCommunityDTO): Observable<Message> {
    return this.http.post<Message>('http://localhost:8080/message/community-post', messageCommunityDto, this.token)
  }
  
  deleteMessage(messageId: number) {
    return this.http.delete(`http://localhost:8080/message/delete/${messageId}`, this.token)
  }

  getMessageById(messageId: number): Observable<Message> {
    return this.http.get<Message>(`http://localhost:8080/message/${messageId}`, this.token)
  }

  putMessage(message: Message): Observable<Message> {
    return this.http.put<Message>('http://localhost:8080/message/edit', message, this.token)
  }

  postMessageOnBusiness(messageBusinessDto: MessageBusinessDTO): Observable<Message>{
    return this.http.post<Message>('http://localhost:8080/message/business-post', messageBusinessDto, this.token)
  }

}
