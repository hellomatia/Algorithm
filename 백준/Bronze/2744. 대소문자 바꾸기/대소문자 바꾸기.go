package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	if scanner.Scan() {
		str := scanner.Text()
		for _, c := range str {
			if strings.ToUpper(string(c)) == string(c) {
				fmt.Print(strings.ToLower(string(c)))
			} else {
				fmt.Print(strings.ToUpper(string(c)))
			}
		}
	}
}