package main

import (
	"fmt"
	"math"
)

var a float64
var b float64

func main() {
	fmt.Scanf("%f %f", &a, &b)
	fmt.Printf("%.0f\n", math.Abs(a-b))
}
