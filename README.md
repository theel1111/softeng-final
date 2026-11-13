# softeng-final

Software Engineering (UPC) final project: a console-based spreadsheet.

## Deliverable 1: Analysis

Due Mon 16 Nov 22:57 on Atenea.

- [ ] Use cases (text)
- [ ] Use case diagram
- [x] Domain model diagram: [`domainModel.drawio.png`](domainModel.drawio.png) (source: `Deliverable1.drawio`)

### Domain Model 
[`/domainModel.drawio.png`](domainModel.drawio.png) (source: `Deliverable1.drawio`)

### Use cases

Actor: **User**

| # | Use case | Notes |
|---|---|---|
| UC1 | Create new spreadsheet | |
| UC2 | Edit cell (number / text / formula) | Error flows: invalid coordinate, formula syntax error, circular dependency, text not convertible to number |
| UC3 | Show spreadsheet | Contents and computed values |
| UC4 | Save spreadsheet to S2V file | Error flow: file can't be written |
| UC5 | Load spreadsheet from S2V file | Error flow: file not found / invalid S2V |
| UC6 | Exit program | |


**