# Arden

This repository contains the source for the Arden language site.

## Documentation standard

This repository documents one language, Arden. Some source pages were written at different times and ended up using inconsistent explanations or terminology.

The source pages are being normalized around one canonical presentation so learners get a single, coherent description of the language.

## Core ideas

- Arden is phonetic and uses the Latin alphabet.
- Default clause order is **subject-verb-object**.
- Verbs use suffix tense: present root, past `-o`, future `-bo`.
- Questions, aspect, and modality are handled with particles such as `kaa`, `ke`, `ken`, `rin`, `sen`, `pren`, `kan`, and `shal`.
- Arden has an explicit copula, `es`, and also permits zero copula in short descriptive clauses.
- Adjectives usually follow the noun and commonly end in `-el`.
- Many vocabulary tables preserve case forms like `-a`, `-am`, `-um`, and plural `-ee`.

## Best entry points

- `site/pages/index.md`
- `site/pages/canonical-grammar.md`
- `site/pages/auxiliary-discourse.md`
- `site/pages/conversational-core.md`
- `site/pages/phoneme-orthography.md`

## Build

Generate the site with:

```bash
./gradlew grim
```
