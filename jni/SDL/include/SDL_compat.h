#ifndef _SDL_compat_h
#define _SDL_compat_h
#ifdef __cplusplus
extern "C" {
#endif
extern DECLSPEC  char *SDLCALL SDL_AndroidGetCachePath();
extern DECLSPEC  void SDLCALL SDL_AndroidEnterGame();
extern DECLSPEC  void SDLCALL SDL_AndroidExitGame();
/* Ends C function definitions when using C++ */
#ifdef __cplusplus
}
#endif
#endif
