```mermaid
flowchart TD
    A[Start] --> B[Convert input string to list of characters]
    B --> C[Count frequency of each character]
    C --> C2[Sort characters by frequency (ascending)]
    C2 --> D[Create leaf node for each character]
    D --> E[Insert all leaf nodes into a list]
    E --> F{More than 1 node left?}
    F -->|Yes| G[Extract two nodes with lowest frequency]
    G --> H[Merge into new internal node]
    H --> I[Insert internal node back into list]
    I --> F
    F -->|No| J[Build complete Huffman Tree]
    J --> K[Assign binary codes by traversing tree]
    K --> L[Encode input using codes]
    L --> M[Output encoded string and code map]
    M --> N[End]
```
