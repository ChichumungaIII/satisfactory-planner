package app.theme

import js.core.jso
import mui.material.AppBarPosition
import mui.material.AppBarProps
import mui.material.ButtonProps
import mui.material.ButtonVariant
import mui.material.CheckboxProps
import mui.material.ContainerProps
import mui.material.FormControlMargin
import mui.material.IconButtonProps
import mui.material.InputBaseMargin
import mui.material.ListItemButtonProps
import mui.material.ListItemProps
import mui.material.ListProps
import mui.material.ListSubheaderColor
import mui.material.ListSubheaderProps
import mui.material.PaletteMode
import mui.material.SelectProps
import mui.material.Size
import mui.material.StackProps
import mui.material.SvgIconColor
import mui.material.SvgIconProps
import mui.material.TextFieldProps
import mui.material.styles.createTheme
import web.cssom.None
import web.cssom.px

private val small = Size.small

private val SatisfactoryMuiTheme = createTheme(jso {
  palette = jso {
    mode = PaletteMode.dark

    primary = jso { main = "#f2c800" }
    secondary = jso { main = "#26d3ec" }

    background = jso {
      default = "#141518"
      paper = "#202125"
    }
  }

  spacing = 4

  typography = jso {
    h1 = jso {
      fontWeight = 500
      fontSize = 36.px
      lineHeight = 40.px
    }
    h2 = jso {
      fontSize = 20.px
    }
  }

  zIndex = jso {
    appBar = 1200
    drawer = 1100
  }

  components = jso {
    MuiAppBar = jso {
      defaultProps = jso<AppBarProps> {
        position = AppBarPosition.sticky
      }
      styleOverrides = jso {
        root = jso {
          backgroundImage = None.none
        }
      }
    }

    MuiAutocomplete = jso {
      styleOverrides = jso {
        option = jso {
          fontSize = 14.px
          padding = "7.5px 16px"
        }
      }
    }

    MuiButton = jso {
      defaultProps = jso<ButtonProps> {
        variant = ButtonVariant.contained
      }
    }

    MuiCheckbox = jso {
      defaultProps = jso<CheckboxProps> {
        size = small
      }
    }

    MuiContainer = jso {
      defaultProps = jso<ContainerProps> {
        maxWidth = false
      }
    }

    MuiIconButton = jso {
      defaultProps = jso<IconButtonProps> {
        size = small
      }
      styleOverrides = jso {
        sizeSmall = jso {
          width = 36.px
          height = 36.px
        }
      }
    }

    MuiList = jso {
      defaultProps = jso<ListProps> {
        dense = true
        disablePadding = true
      }
    }

    MuiListItem = jso {
      defaultProps = jso<ListItemProps> {
        dense = true
        disablePadding = true
      }
    }

    MuiListSubheader = jso {
      defaultProps = jso<ListSubheaderProps> {
        color = ListSubheaderColor.primary
        disableGutters = true
      }
      styleOverrides = jso {
        root = jso {
          padding = "11px 16px"
          fontSize = 14.px
          lineHeight = 14.px
        }
      }
    }

    MuiInputBase = jso {
      styleOverrides = jso {
        root = jso {
          fontSize = 14.px
        }
      }
    }

    MuiListItemButton = jso {
      defaultProps = jso<ListItemButtonProps> {
        dense = true
      }
    }

    MuiLoadingButton = jso {
      defaultProps = jso<ButtonProps> {
        variant = ButtonVariant.contained
      }
    }

    MuiSelect = jso {
      defaultProps = jso<SelectProps<*>> {
        size = small
        margin = InputBaseMargin.dense
      }
    }

    MuiStack = jso {
      defaultProps = jso<StackProps> {
        useFlexGap = true
      }
    }

    MuiTextField = jso {
      defaultProps = jso<TextFieldProps> {
        size = small
        margin = FormControlMargin.dense
      }
    }

    MuiSvgIcon = jso {
      defaultProps = jso<SvgIconProps> {
        color = SvgIconColor.primary
      }
      styleOverrides = jso {
        fontSizeLarge = jso {
          fontSize = 56.px
        }
      }
    }
  }
})

val SatisfactoryAppTheme = AppTheme(SatisfactoryMuiTheme, object : AppThemeConstants {
  override val navigationDrawerWidth = 256.px
  override val toolbarHeight = 64.px
})
