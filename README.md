# Routing with Replicant

This is an example of how to do routing with Replicant.

The [core namespace](./src/parens/core.cljs) wires up a mini-framework with
routing. The [ui namespace](./src/parens/ui.cljc) contains pure functions to
render the UI.

The development build is initialized from [the dev
namespace](./dev/parens/dev.cljs). There is also a [prod
namespace](./src/parens/prod.cljs) which could be used as the target for a
production build.
