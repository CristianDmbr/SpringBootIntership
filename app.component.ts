import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  someVar = "Hello World ! It's Cristian";
  isButtonDisabled = false;
  inputText = "";

  activateButton() {
    this.isButtonDisabled = !this.isButtonDisabled;
  }

  displayInputText(){
    alert('Input value: ' + this.inputText);
  }

  medicine = [
  {id: 1 ,name : "Paracetamol", description: "Pain releave"},
  {id: 2 ,name : "Strepsils", description: "Head releave"}
  ]

}
