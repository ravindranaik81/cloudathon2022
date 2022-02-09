import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  public title!:string;

  public constructor(private readonly http: HttpClient) {

    }

    public async ngOnInit(): Promise<void> {
      // Check if development or prod deployment
      let restUrl = location.origin + '/api/hello?name=Azure';
      if (this.isDevelopment()) {
        restUrl = 'http://localhost:8080/api/hello?name=Angular';
      }
      this.http.get(restUrl, {responseType : 'text'})
        .subscribe(response => {
            this.title = <string>response;
            console.log(this.title);
        });
    }

    private isDevelopment = (): boolean => location.host.includes('localhost');
}
