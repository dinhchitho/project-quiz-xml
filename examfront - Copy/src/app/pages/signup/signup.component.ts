import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent implements OnInit {
  public user = {
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
  };
  constructor(private userService: UserService,private snack:MatSnackBar) {}

  ngOnInit(): void {}
  formSubmit() {
    console.log(this.user);
    if (this.user.username == '' || this.user.username == null) {
      this.snack.open('User is required!','',{
        duration:3000,
      });
      return;
    }
    //validate

    //add user
    this.userService.addUser(this.user).subscribe((data) => {
      //success
      console.log(data);
      Swal.fire('Success done!','User is registered','success');
    },
    (error)=>{
      //error
      console.log(error);
      this.snack.open(error.error.text,'',{
        duration:3000
      })
    }
    )
  }

  //this user
}
