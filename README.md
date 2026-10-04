# Assignment 3 | Bridge Pattern

**Student:** [YOUR_NAME]  
**Group:** [YOUR_GROUP]  
**Topic:** A — Drawing  
**Repository URL:** [YOUR_GITHUB_REPOSITORY_URL]  
**Base commit:** `8a4877597e1589f1293d2fa1f4c78ea0ca725b5a`  
**I3 extension commit:** `a58562b5ab3fd11f84149343ff2a7ece0efd77e6`  
**Final local example commit:** see `LOCAL_COMMIT.txt` in the archive; in a restored repository, run `git rev-parse HEAD`.

## Status and use

This is an AI-assisted learning/reference example, not a claim of student authorship or an already published submission. The course requires students to write and understand their own code and report, cite sources, and use AI only for reference examples and clarification as permitted by the syllabus. Fill in personal details only for your own compliant version. No remote repository was created. [A, section 9]

## Topic and behavior

The application separates shape behavior from rendering descriptions. Demo circles have radius 2; squares have side 3. Renderers return visibly labelled VECTOR, RASTER, or ASCII strings. This is a local textual simulation, not a GUI. [A, section 2]

## Source role map

| Pattern role | Class | Source path |
|---|---|---|
| Abstraction | Shape | src/Shape.java |
| A1 | Circle | src/Circle.java |
| A2 | Square | src/Square.java |
| Implementor | Renderer | src/Renderer.java |
| I1 | VectorRenderer | src/VectorRenderer.java |
| I2 | RasterRenderer | src/RasterRenderer.java |
| I3 | AsciiRenderer | src/AsciiRenderer.java |
| Client | Main | src/Main.java |

## Review locations

| Feature | Exact location |
|---|---|
| Private interface-typed bridge field | src/Shape.java:5 |
| Constructor injection | src/Shape.java:7–10 |
| Public execute contract | src/Shape.java:24 |
| Circle delegation | src/Circle.java:17–19 |
| Square delegation | src/Square.java:17–19 |
| Public implementation replacement | src/Shape.java:20–22 |
| T5 runtime switch and comparisons | src/Main.java:39–66 |
| Reference equality, not ID-only equality | src/Main.java:48 |
| Shared result comparison | src/Main.java:68–74 |
| Calculated status and counters | src/Main.java:76–83 |

All Java classes are in the default package. The source files contain no comments.

## Standard build and run

Open a terminal in the extracted project root. The assignment specifies JDK 17. [A, sections 1, 6]

```bash
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

To recapture the output:

```bash
java -cp out Main --demo > demo-output.txt
```

No Maven, Gradle, GUI, network, database, or external Java dependency is needed. The compiler creates out. Tool parameter references: [J1–J2].

Actual verification environment: javac 21.0.11 with --release 17, OpenJDK 21.0.11 runtime. Separate execution on JDK 17 was not performed. All compiled classes target major version 61. See VERIFICATION.md for real checks and the tested failure path.

## Exact expected results

| Check | Required expected value |
|---|---|
| T1 | VECTOR circle radius=2 |
| T2 | RASTER circle radius=2 |
| T3 | VECTOR square side=3 |
| T4 | RASTER square side=3 |
| T5 | sameObject=true; stateUnchanged=true; ID circle-switch before/after; radius 2 before/after; before=VECTOR circle radius=2; after=RASTER circle radius=2 |
| T6 | ASCII circle radius=2 |
| T7 | ASCII square side=3 |

Full observed output:

```text
T1 PASS | Circle + VectorRenderer | result=VECTOR circle radius=2
T2 PASS | Circle + RasterRenderer | result=RASTER circle radius=2
T3 PASS | Square + VectorRenderer | result=VECTOR square side=3
T4 PASS | Square + RasterRenderer | result=RASTER square side=3
T5 PASS | Circle + VectorRenderer -> RasterRenderer | sameObject=true | stateUnchanged=true | id=circle-switch -> circle-switch | radius=2 -> 2
  before=VECTOR circle radius=2 | after=RASTER circle radius=2
T6 PASS | Circle + AsciiRenderer | result=ASCII circle radius=2
T7 PASS | Square + AsciiRenderer | result=ASCII square side=3
SUMMARY: 7/7 PASS
```

Each status comes from real actual/expected comparisons. T5 also compares object references with == and checks ID/radius preservation. All checks run before the final summary. Any failure produces expected-value diagnostics and process exit code 1. Wrong launch arguments produce Usage without running tests.

IDs distinguish the separate T1/T2 and T3/T4 objects. Their domain dimensions are the same, as required. T5 independently establishes identity of one object.

## The two-stage extension

The base revision includes I1/I2 and an actual 5/5 passing T1–T5 run. The extension adds I3 and T6/T7. Within src/, only AsciiRenderer.java is added and Main.java changes; all six existing hierarchy files remain unchanged. sources.txt is updated separately. [A, section 4]

```bash
git diff 8a4877597e1589f1293d2fa1f4c78ea0ca725b5a HEAD -- src > extension.diff
```

The archived diff was generated after the I3 commit. Later documentation changes do not change the src-only diff. The additional base-demo-output.txt records the real base run.

## Restore the local Git history

The ZIP has root-level assignment files. It also contains git-history.bundle because a source ZIP alone does not retain commits. To inspect the actual example history, from the extracted folder run: [G]

```bash
git clone git-history.bundle bridge-history
cd bridge-history
git log --oneline --all --decorate
git diff base-i1-i2 HEAD -- src
```

Tags: base-i1-i2 and extension-i3. The example commit author is explicitly Bridge Study Example, not the student. LOCAL_COMMIT.txt and the bundle are untracked packaging metadata; the source and study documents match the final tracked local tree. No remote GitHub URL exists yet.

For an actual submission, develop your own understood version, fill in README/report identification and the real repository URL, rebuild, refresh evidence, and supply the actual submitted commit hash in Moodle. Rename the final ZIP as Assignment3_Group_Surname_Name.zip with your own information. The source must match that submitted commit. [A, section 6]

## Documentation map

GUIDE_RU.md and GUIDE_EN.md explain all 186 physical Java source lines, startup, method calls, state changes, theory, tests, Git workflow, and defense questions. GUIDE_RU_EN.md combines both languages.

report.pdf is a three-page English reference report; report.md is editable content. UML is supplied as PNG, SVG, PlantUML, and Graphviz source. It reflects the final eight classes/interfaces. No UML tool is needed to run the Java program.

## Design boundary

The shared Renderer contract contains renderCircle and renderSquare. Adding another renderer is independent of the shape hierarchy. Adding a new low-level shape primitive would require expanding this simple contract. This trade-off keeps the example explicit and beginner-readable rather than claiming unlimited extension with an unchanged interface.

## References

[A] Astana IT University. Assignment 3 | Bridge Pattern. Supplied Assignment_3_Bridge_Pattern_Instructions-1.pdf, sections 1–9. Requirements only; the unavailable Lecture 4 is not reconstructed.

[P] Apache Software Foundation. Apache Calcite API, Glossary: BRIDGE_PATTERN and ADAPTER_PATTERN. `https://calcite.apache.org/javadocAggregate/org/apache/calcite/util/Glossary.html`

[J1] Oracle. Java SE 17, The java Command. `https://docs.oracle.com/en/java/javase/17/docs/specs/man/java.html`

[J2] Oracle. Java SE 17, The javac Command. `https://docs.oracle.com/en/java/javase/17/docs/specs/man/javac.html`

[G] Git project. git-bundle documentation. `https://git-scm.com/docs/git-bundle`
