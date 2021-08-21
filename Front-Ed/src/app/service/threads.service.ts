import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Threads } from '../model/Threads';
import { ThreadsDTO } from '../model/ThreadsDTO';

@Injectable({
  providedIn: 'root'
})
export class ThreadsService {

  constructor(
    private http: HttpClient
  ) { }

  token = {
    headers: new HttpHeaders().set("Authorization", environment.token)
  }

  getThreadsById(threadsId: number): Observable<Threads> {
    return this.http.get<Threads>(`http://localhost:8080/threads/${threadsId}`, this.token);
  }

  createThreads(threadsDto: ThreadsDTO): Observable<Threads> {
    return this.http.post<Threads>("http://localhost:8080/threads/create-threads", threadsDto, this.token);
  }

}
