package main 

import (
	"fmt"      
	"io"       
	"net/http" 
	"sync"     
)

type FetchResult struct {
	URL        string
	StatusCode int
	Size       int
	Error      error
}

func worker(id int, jobs <-chan string, results chan<- FetchResult, wg *sync.WaitGroup) {
	defer wg.Done()

	for url := range jobs {
		// Fetch the URL
		resp, err := http.Get(url)

		// Create result struct
		result := FetchResult{URL: url}

		if err != nil {
			// Request failed
			result.Error = err
		} else {
			// Request succeeded
			result.StatusCode = resp.StatusCode

			// Read the body to get size
			body, err := io.ReadAll(resp.Body)
			resp.Body.Close()

			if err != nil {
				result.Error = err
			} else {
				result.Size = len(body)
			}
		}

		// Send result back
		results <- result
	}
}

func main() {
	// List of URLs to fetch
	urls := []string{
		"https://example.com",
		"https://golang.org",
		"https://uottawa.ca",
		"https://github.com",
		"https://httpbin.org/get",
	}

	numWorkers := 3

	jobs := make(chan string, len(urls))
	results := make(chan FetchResult, len(urls))

	var wg sync.WaitGroup

	fmt.Println("Fetching URLs concurrently using worker pool...")

	for w := 1; w <= numWorkers; w++ {
		wg.Add(1)
		go worker(w, jobs, results, &wg)
	}

	for _, url := range urls {
		jobs <- url
	}
	close(jobs)

	go func() {
		wg.Wait()
		close(results)
	}()

	for result := range results {
		if result.Error != nil {
			fmt.Printf("%s | Error: %v\n", result.URL, result.Error)
		} else {
			fmt.Printf("%s | Status: %d | Size: %d bytes\n",
				result.URL, result.StatusCode, result.Size)
		}
	}

	fmt.Println("\nScraping complete!")
}
