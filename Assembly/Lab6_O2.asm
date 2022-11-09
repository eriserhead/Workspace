dosseg
    .model small
    .stack 0100h
    
.data

.code
    start: 

    mov ax, @data   ;to read .data section
    mov ds, ax

    mov ah, 2       ;declare character
    mov dl, 'a'     ;display variable
    mov cx, 26       ;number of loops
    
    l1:
        int 21h ;print variable
        mov bl, dl  ;temporary put dl variable to bl
        mov dl, 0ah ;we can now use dl to nextline
        int 21h     ;print nextline
        mov dl, bl  ;transfer back bl to dl

        inc dl      ;decrement dl variable
    loop l1

    mov ax, 4c00h ;to end the code
    int 21h
    end start

