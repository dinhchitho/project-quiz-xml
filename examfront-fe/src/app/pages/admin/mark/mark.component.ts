import { HttpClient } from '@angular/common/http';
import { MarkService } from './../../../services/mark.service';
import { Component, OnInit } from '@angular/core';
import baseUrl from 'src/app/services/helper';
import { switchMap } from 'rxjs/operators';
@Component({
  selector: 'app-mark',
  templateUrl: './mark.component.html',
  styleUrls: ['./mark.component.scss']
})
export class MarkComponent implements OnInit {
  users:any=[];
  marks:any=[];

  constructor(private markService:MarkService, private httpClient:HttpClient) { }

  ngOnInit(): void {
    this.markService.getAllMarks().subscribe(res => {
      if (res) {
        console.log(res);
        this.users = res; 
      }
    })
    //this.getMarksXml();
  }

  getMarksXml() {
    return this.httpClient
      .get(`${baseUrl}/mark/`, { responseType: "text" })
      .pipe(
        switchMap(async xml => await this.parseXmlToJson(xml))
      );
  }
  deleteQuiz() {

  }
  async parseXmlToJson(xml:any) {
    // With parser
    /* const parser = new xml2js.Parser({ explicitArray: false });
    parser
      .parseStringPromise(xml)
      .then(function(result) {
        console.log(result);
        console.log("Done");
      })
      .catch(function(err) {
        // Failed
      }); */

    // Without parser
    // return await xml2js
    //   .parseStringPromise(xml, { explicitArray: false })
    //   .then((response: any) => response);
  }
  
}
