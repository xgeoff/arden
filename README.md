# Arden

This repository contains the source for the Arden language site.

## Current documentation standard

The repo historically mixed two different versions of Arden:

1. an older morphology-heavy draft with tense suffixes
2. a newer conversational draft with invariant verbs and particles

The source pages are now being normalized around the **conversational draft** because it is the clearest path for learners and it matches the newer grammar pages.

## Core ideas

- Arden is phonetic and uses the Latin alphabet.
- Default clause order is **subject-verb-object**.
- Verbs are typically **invariant** in conversational use.
- Questions, aspect, and modality are handled with particles such as `kaa`, `ke`, `rin`, `sen`, `pren`, `kan`, and `shal`.
- Arden often uses a **zero copula** for simple states.
- Adjectives usually follow the noun and commonly end in `-el`.
- Many vocabulary tables preserve older case forms like `-a`, `-am`, `-um`, and plural `-ee`; treat those as an expansion layer, not the first thing a learner needs.

## Best entry points

- `site/pages/index.md`
- `site/pages/auxiliary-discourse.md`
- `site/pages/conversational-core.md`
- `site/pages/phoneme-orthography.md`

## Build

Generate the site with:

```bash
./gradlew grim
```
