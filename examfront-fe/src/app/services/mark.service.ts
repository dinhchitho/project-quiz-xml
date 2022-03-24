import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
// import * as xml2js from 'xml2js';
import { switchMap } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class MarkService {

  constructor(private _http: HttpClient) { }

  public getAllMarks(){
    const opts : any = {
      headers: new HttpHeaders({
        'Accept': 'text/html, application/xhtml+xml, */*',
        'Content-Type': 'text/plain; charset=utf-8'
      }),
      responseType: 'text'
    };
    return this._http.get(`${baseUrl}/mark/`, opts);
  }
  
  //async parseXmlToJson(xml: any) {
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
  //   return await xml2js
  //     .parseStringPromise(xml, { explicitArray: false })
  //     .then(response => response.Employees.Employee);
  // }
}
