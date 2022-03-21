import { Component, OnInit } from '@angular/core';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.scss']
})
export class ViewQuizzesComponent implements OnInit {

  quizzes:any=[
    {
      qId:23,
      title:'Basic Java Quiz',
      description:'JAVA CORE là kiến thức nền tảng của ngôn ngữ lập trình JAVA, nó sẽ là bước khởi đầu để bạn có thể học những kiến thức nâng cao như: JSP- Servlet - Android.',
      maxMarks:'50',
      numberOfQuestions:'20',
      active:'',
      category:{
        title:'Programming 1'
      }
    },
    {
      qId:23,
      title:'Basic Java Quiz',
      description:'JAVA CORE là kiến thức nền tảng của ngôn ngữ lập trình JAVA, nó sẽ là bước khởi đầu để bạn có thể học những kiến thức nâng cao như: JSP- Servlet - Android.',
      maxMarks:'50',
      numberOfQuestions:'20',
      active:'',
      category:{
        title:'Programming 2'
      }
    }
  ]

  constructor(private _quiz: QuizService) { }

  ngOnInit(): void {
    this._quiz.quizzes().subscribe((data: any) => {
      this.quizzes=data;
    },
    (error)=>{
      console.log(error);
      Swal.fire('Error !!','error in loading data ','error');
    }
    );
  }

  public deleteQuiz(qId:any){
    Swal.fire({
      icon:'info',
      title:'Are you sure?',
      confirmButtonText:'Delete',
      showCancelButton:true,
    }).then((result)=>{
      if(result.isConfirmed){
        this._quiz.deleteQuiz(qId).subscribe(
          (data:any) =>{
            this.quizzes=this.quizzes.filter((quiz:any)=>quiz.qid != qId);
            Swal.fire('Success','Quiz Deleted','success');
          },
          (error)=>{
            Swal.fire('Error','Error in deleting quiz','error');
          }
        );
      }
    });
  }

}
