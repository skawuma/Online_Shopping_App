import { Directive, ElementRef, EventEmitter, HostBinding, HostListener, Input, Output, Renderer2 } from '@angular/core';
import { FileHandle } from './_model/file-handle.model';
import { DomSanitizer } from '@angular/platform-browser';

@Directive({
  selector: '[appDrag]'
})
export class DragDirective {

  @HostBinding('style.border') private borderStyle = '2px dashed';
  @HostBinding('style.border-color') private borderColor = '#696D7D';
  @HostBinding('style.border-radius') private borderRadius = '5px';

  
  @Output() files:EventEmitter<FileHandle> = new EventEmitter();
  //@Output() onDropFile = new EventEmitter<File>();

  @HostBinding("style.background")private background = "#eee";

  constructor( private sanitizer: DomSanitizer,
    // private renderer: Renderer2,
    // private elementRef: ElementRef,
    
    ) 
  
  { 
   
    // this.multiple = false;
    // this.accept = ['application/pdf'];
    // this.maxFileSize = 1;
    // this.renderer.setProperty(this.elementRef.nativeElement, 'draggable', true);


  }

  @HostListener("dragover",["$event"])
  public onDragOver(evt: DragEvent){

   evt.preventDefault();
   evt.stopPropagation();
   this.background ="#999";
   
  }
  
  @HostListener("dragleave",["$event"])
  public onDragLeave(evt: DragEvent){

    evt.preventDefault();
    evt.stopPropagation();
    this.background ="#eee";

  }

   
//   @HostListener("drop",["$event"])
//   public onDrop2(evt:DragEvent){
   
//     evt.preventDefault();
//     evt.stopPropagation();
//     this.background ="#eee"; 
   

//     console.log("event: ", evt.dataTransfer?.files.length);
//     // files = evt.dataTransfer?.files;



//     if (evt.dataTransfer!.files.length > 0) {
//       let reader: FileReader = new FileReader();

//       reader.readAsDataURL(evt.dataTransfer!.files[0]);
//       reader.onloadstart = (e) => {
//         console.log('load start', this.multiple, this.accept, this.maxFileSize);
//       }

//   reader.onprogress = (e) => {
//     console.log('load progress');
//   }
// }


// }




  @HostListener("drop", ["$event"])
  public onDrop(evt: DragEvent) {

    evt.preventDefault();
    evt.stopPropagation();
    this.background = "#eee";
    this.borderColor = '#696D7D';
    this.borderStyle = '2px dashed';

    let fileHandle: FileHandle;

    const file: any = evt.dataTransfer?.files[0];

    const url = this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(file));

    fileHandle = { file, url };
    this.files.emit(fileHandle);


  }    
}