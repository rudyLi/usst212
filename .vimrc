set runtimepath^=~/.vim/bundle/ctrlp.vim
map ca :Calendar<cr>
:set cc=80
nmap <F12> <Esc>:CtrlPBuffer<CR>
inoremap ( ()<ESC>i
inoremap [ []<ESC>i
inoremap { {}<ESC>i
inoremap < <><ESC>i
let g:indent_guides_guide_size=2
let g:indentLine_color_term = 239
set ts=2 sw=2 et
set cursorcolumn
set cursorline
let mapleader = ","
nmap <F3> :NERDTreeToggle<CR>
noremap <F7> :GundoToggle<CR>
noremap <F6> :LUWalk<CR>
noremap <silent><F4> :Grep<CR>
let showmarks_enable = 1
 let showmarks_include = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
 " Ignore help, quickfix, non-modifiable buffers
 let showmarks_ignore_type = "hqm"
 " Hilight lower & upper marks
 let showmarks_hlline_lower = 1
 let showmarks_hlline_upper = 1 
let g:LookupFile_AlwaysAcceptFirst = 1
set guifont=Monaco:h10
set backspace=2
set cindent shiftwidth=4
set smartindent
set ai!
set nu!
set ruler
set incsearch
set hlsearch
set nowrapscan
set nocompatible
set list
set listchars=tab:>-,trail:-
set nocompatible
syntax on
filetype on
filetype indent on
filetype plugin on
filetype plugin indent on
set expandtab
set tabstop=2 shiftwidth=2 softtabstop=2
set autoindent
let g:rubycomplete_buffer_loading = 1
let g:rubycomplete_classes_in_global = 1
let g:rubycomplete_rails = 1
filetype off
let g:winManagerWindowLayout='FileExplorer|Taglist'
nmap wm :WMToggle<cr>
let Tlist_Show_One_File=1
let Tlist_Exit_OnlyWindow=1
let g:SuperTabRetaincompletionType=2
set autochdir
autocmd FileType ruby compiler ruby
set rtp+=~/.vim/bundle/vundle/
call vundle#rc()
Bundle 'gmarik/vundle'
Bundle 'tpope/vim-fugitive'
Bundle 'Lokaltog/vim-easymotion'
Bundle 'rstacruz/sparkup',{'rtp':'vim/'}
Bundle 'tpope/vim-rails.git'
Bundle 'desert256.vim'
Bundle 'L9'
Bundle 'FuzzyFinder'
Bundle 'git://git.wincent.com/command-t.git'
Bundle 'vim-ruby/vim-ruby'
Bundle 'taglist.vim'
Bundle 'SuperTab'
Bundle 'winmanager'
Bundle 'nerdtree'
Bundle 'gundo'
Bundle 'kchmck/vim-coffee-script'
Bundle 'vimwiki'
Bundle 'Yggdroot/indentLine'
Bundle 'mattn/calendar-vim'
set nocompatible
filetype plugin indent on
