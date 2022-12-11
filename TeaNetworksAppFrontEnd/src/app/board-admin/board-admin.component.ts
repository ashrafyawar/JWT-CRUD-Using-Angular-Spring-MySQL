import { Component, OnInit } from '@angular/core';
import { UserInfo } from '../models/UserInfo';
import { WeatherData } from '../models/WeatherData';
import { WeatherDataFilter } from '../models/WeatherDataFilter';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {
  content?: string;
  weatherDataList: WeatherData[] | undefined;
  endUserList: UserInfo[] | undefined;
  filterVal: string | undefined;
  weatherDataFilter = new WeatherDataFilter();
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.weatherDataFilter.isAll = true;
    this.userService.getAdminBoard().subscribe({
      next: data => {
        this.content = data.message;
      },
      error: err => {
        this.content = "Can't Access This Page !!!";
      }
    });

    this.userService.getEndUserList().subscribe({
      next: data => {
        this.endUserList = data;
      },
      error: err => {
        this.content = "Can't Get The Weather List Data !!!";
      }
    });

    this.userService.getWeatherList(this.weatherDataFilter).subscribe({
      next: data => {
        this.weatherDataList = data;
      },
      error: err => {
        this.content = "Can't Get The Weather List Data !!!";
      }
    });
  }

  fetchWeatherData(){
    this.weatherDataList = [];
    this.weatherDataFilter.isAll = true;
    this.userService.getWeatherList(this.weatherDataFilter).subscribe({
      next: data => {
        this.weatherDataList = data;
      },
      error: err => {
        this.content = "Can't Get The Weather List Data !!!";
      }
    });
  }

  fetchWeatherDataCondition(){
    this.weatherDataList = [];
    this.weatherDataFilter.isAll = false;
    this.weatherDataFilter.filterType = "condition";
    this.weatherDataFilter.filterVal = this.filterVal;
    this.userService.getWeatherList(this.weatherDataFilter).subscribe({
      next: data => {
        this.weatherDataList = data;
      },
      error: err => {
        this.content = "Can't Get The Weather List Data !!!";
      }
    });
  }

  fetchWeatherDataLocation(){
    this.weatherDataList = [];
    this.weatherDataFilter.isAll = false;
    this.weatherDataFilter.filterType = "location";
    this.weatherDataFilter.filterVal = this.filterVal;
    this.userService.getWeatherList(this.weatherDataFilter).subscribe({
      next: data => {
        this.weatherDataList = data;
      },
      error: err => {
        this.content = "Can't Get The Weather List Data !!!";
      }
    });
  }
  
  fetchWeatherDataAverage(){
    this.weatherDataList = [];
    this.weatherDataFilter.isAll = false;
    this.weatherDataFilter.filterType = "average";
    this.weatherDataFilter.filterVal = this.filterVal;
    this.userService.getWeatherList(this.weatherDataFilter).subscribe({
      next: data => {
        this.weatherDataList = data;
      },
      error: err => {
        this.content = "Can't Get The Weather List Data !!!";
      }
    });
  }
}
