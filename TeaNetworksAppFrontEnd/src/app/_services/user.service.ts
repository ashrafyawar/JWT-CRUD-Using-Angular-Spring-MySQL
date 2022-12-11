import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserInfo } from '../models/UserInfo';
import { WeatherDataFilter } from '../models/WeatherDataFilter';

const API_URL = 'http://localhost:8080/api/test/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', httpOptions);
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user',httpOptions);
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', httpOptions);
  }

  getWeatherList(weatherDataFilter: WeatherDataFilter): Observable<any> {
    return this.http.post(API_URL + 'weather',weatherDataFilter, httpOptions);
  }
  getEndUserList(): Observable<any> {
    return this.http.get(API_URL + 'end-user-list', httpOptions);
  }


  updateUser(userId: number, updateUser: UserInfo): Observable<any> {
    const params = new HttpParams().set('id', userId);
    return this.http.put(API_URL + 'user',{ params }, httpOptions);
  }

  deleteUser(userId: number): Observable<any> {
    const params = new HttpParams().set('id', userId);
    return this.http.delete(API_URL + 'user',{ params });
  }
}
